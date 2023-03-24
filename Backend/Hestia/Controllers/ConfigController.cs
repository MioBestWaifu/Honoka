using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Hestia.Controllers
{
    public partial class ConfigController : Controller
    {
        public Label currentIpLabel;
        public Entry serverPortEntry, DbIpEntry, DbPortEntry, DbUserEntry, DbPasswordEntry, DbNameEntry;

        [ObservableProperty]
        string ip = "IP: 127.0.0.1";

        public void TryTurnServerOn(Button btn)
        {
            if (Server.TurnServerOn())
            {
                btn.BackgroundColor = Colors.Green;
                btn.Text = "SERVER ON";
            }
        }

        public void TryTurnServerOff(Button btn)
        {
            if (Server.TurnServerOff())
            {
                btn.BackgroundColor = Colors.Red;
                btn.Text = "SERVER OFF";
            }
        }

        public void TryConnectToDatabase(Button btn) {
            if (Server.ConnectDatabase())
            {
                btn.BackgroundColor = Colors.Green;
                btn.Text = "DATABASE ON";
            }
        }

        public void TryDisconnectFromDatabase(Button btn)
        {
            if (Server.DisconnectDatabase())
            {
                btn.BackgroundColor = Colors.Red;
                btn.Text = "DATABASE OFF";
            }
        }
    }
}
