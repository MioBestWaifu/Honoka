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
        public Entry serverPortEntry, dbIpEntry, dbPortEntry, dbUserEntry, dbPasswordEntry, dbNameEntry;
        private Server server;

        [ObservableProperty]
        string ip = "IP: 127.0.0.1";

        public ConfigController(Server server) {
            this.server = server;
        }

        [RelayCommand]
        void TryChangeServerStatus(Button btn)
        {
            if (!Server.isServerOn)
            {
                if (server.TurnServerOnAsync(Int32.Parse(serverPortEntry.Text)).Result)
                {
                    btn.BackgroundColor = Colors.Green;
                    btn.Text = "SERVER ON";
                }
            } else
            {
                if (server.TurnServerOff())
                {
                    btn.BackgroundColor = Colors.Red;
                    btn.Text = "SERVER OFF";
                }
            }
        }

        [RelayCommand]
        void TryChangeDatabaseStatus(Button btn) {
            if (!Server.isDatabaseConnected)
            {
                if (server.ConnectDatabase(dbIpEntry.Text,dbUserEntry.Text,dbPasswordEntry.Text,dbNameEntry.Text,dbPortEntry.Text))
                {
                    btn.BackgroundColor = Colors.Green;
                    btn.Text = "ON";
                }
            } else
            {
                if (server.DisconnectDatabase())
                {
                    btn.BackgroundColor = Colors.Red;
                    btn.Text = "OFF";
                }
            }
        }

        [RelayCommand]
        void TryWakeServiceBots(Button btn)
        {

        }

        [RelayCommand]
        void TryWakeClientBots(Button btn)
        {

        }

        [RelayCommand]
        void ClearHistory()
        {

        }

        [RelayCommand]
        void ClearAll()
        {

        }

    }
}
