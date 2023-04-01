using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace Hestia.Scripts
{
    public class RequestsSession : HttpSession
    {
        JsonSerializerOptions jsonOptions = new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        };
        public RequestsSession(HttpServer server) : base(server)
        {
        }

        protected override async void OnReceivedRequest(HttpRequest request)
        {
            base.OnReceivedRequest(request);
            if (request.Method == "HEAD")
                SendResponseAsync(Response.MakeHeadResponse());
            else if (request.Method == "GET")
            {
                if (request.Url.Contains("styles"))
                {
                    SendResponseAsync(Response.MakeGetResponse(Scripts.Server.Styles, "text/css; charset=UTF-8"));
                }
                else if (request.Url.Contains("runtime"))
                {
                    SendResponseAsync(Response.MakeGetResponse(Scripts.Server.Runtime, "text/javascript; charset=UTF-8"));
                }
                else if (request.Url.Contains("main"))
                {
                    SendResponseAsync(Response.MakeGetResponse(Scripts.Server.Main, "text/javascript; charset=UTF-8"));
                }
                else if (request.Url.Contains("polyfills"))
                {
                    SendResponseAsync(Response.MakeGetResponse(Scripts.Server.Polyfills, "text/javascript; charset=UTF-8"));
                }
                else if (request.Url.Contains("favicon"))
                {
                    SendResponseAsync(Response.MakeGetResponse(Scripts.Server.Favicon, "message/http; charset=UTF-8"));
                }
                else if (request.Url.Contains("login"))
                {
                    SendResponseAsync(Response.MakeGetResponse(JsonSerializer.Serialize(new UserInformation(-1)), "application/json;charset=UTF-8"));
                }
                else
                {
                    SendResponseAsync(Response.MakeGetResponse(Scripts.Server.Index, "text/html; charset=UTF-8"));
                }
            }
            else if (request.Method == "POST")
            {
                if (request.Url.Contains("login"))
                {
                    UserInformation info = JsonSerializer.Deserialize<UserInformation>(request.Body, jsonOptions);
                    if (await CheckLoginAsync(info.Email, info.Password))
                    {
                        SendResponseAsync(Response.MakeGetResponse(JsonSerializer.Serialize(await GetUserInformationAsync(info.Email)), "application/json;charset=UTF-8"));
                    }
                    else
                    {
                        SendResponseAsync(Response.MakeGetResponse(JsonSerializer.Serialize(new UserInformation(-1)), "application/json;charset=UTF-8"));
                    }
                }
                else if (request.Url.Contains("getUser"))
                {

                }
                else if (request.Url.Contains("registering"))
                {
                    if (await TryToRegisterUserAsync(JsonSerializer.Deserialize<UserInformation>(request.Body, jsonOptions)))
                        SendResponseAsync(Response.MakeGetResponse("DONE"));
                    else
                        SendResponseAsync(Response.MakeGetResponse("FAILED"));
                }

            }
        }

        private async Task<bool> CheckLoginAsync(string email, string password)
        {
            MySqlParameter[] toPass = new MySqlParameter[2];
            toPass[0] = new MySqlParameter("email", email);
            toPass[1] = new MySqlParameter("password", password);
            try
            {
                var reader = await MySqlHelper.ExecuteReaderAsync(Scripts.Server.connectionString, "SELECT user.idUser FROM user WHERE user.email = @email AND user.password = @password", toPass);
                return reader.HasRows;
            } catch (MySqlException ex)
            {
                return false;
            }
        }

        private async Task<UserInformation> GetUserInformationAsync(string email)
        {
            try
            {
                var reader = await MySqlHelper.ExecuteReaderAsync(Scripts.Server.connectionString, "SELECT * FROM user WHERE user.email = @email", new MySqlParameter("email", email));
                reader.Read();
                return new UserInformation(reader.GetString(1), reader.GetString(2), "NULL", "NULL", reader.GetDateTime(5));
            } catch (Exception ex)
            {
                return null;
            }
            
        }

        private async Task<UserInformation> GetUserInformationAsync(int id)
        {
            return null;
        }

        private async Task<bool> TryToRegisterUserAsync(UserInformation info)
        {
            MySqlParameter[] toPass = new MySqlParameter[4];
            toPass[0] = new MySqlParameter("name",info.Username);
            toPass[1] = new MySqlParameter("email", info.Email);
            toPass[2] = new MySqlParameter("password", info.Password);
            toPass[3] = new MySqlParameter("date", info.Birthday);
            try
            {
                await MySqlHelper.ExecuteNonQueryAsync(Scripts.Server.connectionString, "INSERT INTO user (aluguel.user.name,aluguel.user.email,aluguel.user.password,aluguel.user.genre,aluguel.user.birthday,aluguel.user.providingService,aluguel.user.area) " +
                    "VALUES(@name,@email,@password,NULL,@date,NULL,NULL)", toPass);
                return true;
            } catch (MySqlException ex)
            {
                return false;
            }
        } 
    }
}
