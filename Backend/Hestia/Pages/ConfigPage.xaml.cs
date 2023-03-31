using Hestia.Controllers;
using Microsoft.Maui.Graphics;

namespace Hestia.Pages;

public partial class ConfigPage : ContentPage
{
    ConfigController controller;
    public ConfigPage()
    {
        InitializeComponent();
    }
    public ConfigPage(ConfigController controller)
    {
        InitializeComponent();
        this.controller = controller;
        this.BindingContext = controller;
    }
    protected override void OnSizeAllocated(double width, double height)
    {
        base.OnSizeAllocated(width, height);
        controller.Height = (int)height;
        controller.Width = (int)width;

    }

    private void Ip_Entry_Loaded(object sender, EventArgs e)
    {
        controller.dbIpEntry = sender as Entry;
    }

    private void DB_Port_Entry_Loaded(object sender, EventArgs e)
    {
        controller.dbPortEntry = sender as Entry;
    }

    private void Server_Port_Entry_Loaded(object sender, EventArgs e)
    {
        controller.serverPortEntry = sender as Entry;
    }

    private void User_Entry_Loaded(object sender, EventArgs e)
    {
        controller.dbUserEntry = sender as Entry;
    }

    private void Password_Entry_Loaded(object sender, EventArgs e)
    {
        controller.dbPasswordEntry = sender as Entry;
    }

    private void DB_Entry_Loaded(object sender, EventArgs e)
    {
        controller.dbNameEntry = sender as Entry;
    }

}