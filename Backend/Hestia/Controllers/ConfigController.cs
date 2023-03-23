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
        public Entry serverPortEntry, DbIpEntry, DbPortEntry, DbUserEntry, DbPasswordEntry,DbNameEntry;

        [RelayCommand]
        void ConnectToDatabase()
        {

        }
    }
}
