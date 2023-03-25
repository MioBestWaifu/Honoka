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

        [RelayCommand]
        void TryChangeServerStatus(Button btn)
        {
            if (!Server.isServerOn)
            {
                if (Server.TurnServerOn())
                {
                    btn.BackgroundColor = Colors.Green;
                    btn.Text = "SERVER ON";
                }
            } else
            {
                if (Server.TurnServerOff())
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
                if (Server.ConnectDatabase())
                {
                    btn.BackgroundColor = Colors.Green;
                    btn.Text = "DATABASE ON";
                }
            } else
            {
                if (Server.DisconnectDatabase())
                {
                    btn.BackgroundColor = Colors.Red;
                    btn.Text = "DATABASE OFF";
                }
            }
        }

        [RelayCommand]
        void TryWakeServiceBots(Button btn)
        {

        }

    }
}
