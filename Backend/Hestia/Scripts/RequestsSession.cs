using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Hestia.Scripts
{
    public class RequestsSession : HttpSession
    {
        public RequestsSession(HttpServer server) : base(server)
        {
        }

        protected override void OnReceivedRequest(HttpRequest request)
        {
            base.OnReceivedRequest(request);
            if (request.Method == "HEAD")
                SendResponseAsync(Response.MakeHeadResponse());
            else if (request.Method == "POST")
            {
                var y = request.Body;
                Console.WriteLine("CONNECTED");
                
            }
        }
    }
}
