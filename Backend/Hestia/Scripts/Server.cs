using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using Windows.Devices.Printers.Extensions;

namespace Hestia.Scripts
{
    public class Server 
    {
        private RequestsOpener opener;
        public static bool isServerOn = false;
        public static bool isDatabaseConnected = false;
        public static string connectionString = "";
        private static byte[] index, polyfiils, runtime, main, styles, favicon;

        public static byte[] Index { get => index;}
        public static byte[] Polyfills { get => polyfiils;}
        public static byte[] Runtime { get => runtime;}
        public static byte[] Main { get => main; }
        public static byte[] Styles { get => styles; }
        public static byte[] Favicon { get => favicon; }

        public Server()
        {
            var basePath = Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location);
            index = File.ReadAllBytes(basePath + "/index.html");
            main = File.ReadAllBytes(basePath + "/main.js");
            runtime = File.ReadAllBytes(basePath + "/runtime.js");
            polyfiils = File.ReadAllBytes(basePath + "/polyfills.js");
            styles = File.ReadAllBytes(basePath + "/styles.css");
            favicon = File.ReadAllBytes(basePath + "/favicon.ico");
        }

        public async Task<bool> TurnServerOnAsync(int port)
        {
            try {
                opener = new RequestsOpener(IPAddress.Any, port);
                isServerOn = true;
                opener.Start();
                return true;
            } catch (Exception ex)
            {
                return false;
            }
        }

        public bool TurnServerOff()
        {
            isServerOn = false;
            return true;
        }

        public bool ConnectDatabase(string server, string uid, string pwd, string database, string port)
        {
            connectionString = $"server={server};uid={uid};pwd={pwd};database={database};port={port}";
            try
            {
                MySqlParameter[] toPass = new MySqlParameter[2];
                toPass[0] = new MySqlParameter("email", "Yanbarril@hotmail.com");
                toPass[1] = new MySqlParameter("password", "sexo");
                var reader = MySqlHelper.ExecuteReaderAsync(connectionString, "SELECT user.idUser FROM user WHERE user.email = @email AND user.password = @password", toPass).Result;
                if (reader.HasRows) {
                    isDatabaseConnected = true;
                    return true;
                } else
                {
                    return false;
                }
            } catch (Exception ex){
                return false;
            }
        }

        public bool DisconnectDatabase()
        {
            isDatabaseConnected = false;
            return true;
        }
        private async Task<byte[]> ReadFile(string filePath)
        {
            using var stream = await FileSystem.OpenAppPackageFileAsync("AboutAssets.txt");
            using var reader = new StreamReader(stream);

            var contents = reader.ReadToEnd();

            return null;
        }
    }
}
