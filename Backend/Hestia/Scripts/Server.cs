using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Hestia.Scripts
{
    public class Server 
    {
        private RequestsOpener opener;
        public static bool isServerOn = false;
        public static bool isDatabaseConnected = false;

        public Server()
        {
            opener = new RequestsOpener(IPAddress.Any, 80);
            opener.Start();
        }

        public static bool TurnServerOn()
        {
            isServerOn = true;
            return true;
        }

        public static bool TurnServerOff()
        {
            isServerOn = false;
            return true;
        }

        public static bool ConnectDatabase()
        {
            isDatabaseConnected = true;
            return true;
        }

        public static bool DisconnectDatabase()
        {
            isDatabaseConnected = false;
            return true;
        }
    }
}
