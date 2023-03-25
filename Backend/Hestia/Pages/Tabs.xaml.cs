namespace Hestia.Pages;

public partial class Tabs : TabbedPage
{
	public Tabs(AdminPage adm, ConfigPage config, MonitoringPage monitoring)
	{
		InitializeComponent();
        Children.Add(monitoring);
        Children.Add(config);
    }
}