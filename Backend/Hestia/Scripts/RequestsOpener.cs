using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Hestia.Scripts
{
    public class RequestsOpener : HttpServer
    {
        public RequestsOpener(IPAddress address, int port) : base(address, port)
        {
        }

        protected override TcpSession CreateSession() { return new RequestsSession(this); }


    }
}
