namespace Hestia;

public partial class App : Application
{
	public App()
	{
		InitializeComponent();

		MainPage = new AppShell();
        Routing.RegisterRoute(nameof(AdminPage), typeof(AdminPage));
        Routing.RegisterRoute(nameof(ConfigPage), typeof(ConfigPage));
        Routing.RegisterRoute(nameof(MonitoringPage), typeof(MonitoringPage));
        Routing.RegisterRoute(nameof(Tabs), typeof(Tabs));
    }
}
