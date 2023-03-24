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
        controller.DbIpEntry = sender as Entry;
    }

    private void DB_Port_Entry_Loaded(object sender, EventArgs e)
    {
        controller.DbPortEntry = sender as Entry;
    }

    private void Server_Port_Entry_Loaded(object sender, EventArgs e)
    {
        controller.serverPortEntry = sender as Entry;
    }

    private void User_Entry_Loaded(object sender, EventArgs e)
    {
        controller.DbUserEntry = sender as Entry;
    }

    private void Password_Entry_Loaded(object sender, EventArgs e)
    {
        controller.DbPasswordEntry = sender as Entry;
    }

    private void DB_Entry_Loaded(object sender, EventArgs e)
    {
        controller.DbNameEntry = sender as Entry;
    }


    private void Server_Button_Pressed(object sender, EventArgs e)
    {
        Button btn = sender as Button;
        if (!Server.isServerOn)
        {
            controller.TryTurnServerOn(btn);
        }
        else
        {
            controller.TryTurnServerOff(btn);
        }
    }

    private void DB_Button_Pressed(object sender, EventArgs e)
    {
        Button btn = sender as Button;
        if (!Server.isDatabaseConnected)
        {
            controller.TryConnectToDatabase(btn);
        }
        else
        {
            controller.TryDisconnectFromDatabase(btn);
        }
    }

}