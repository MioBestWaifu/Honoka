global using Hestia.Scripts;
global using Hestia.Pages;
global using Hestia.Controllers;
global using MySql.Data;
global using NetCoreServer;
global using System.Net;
using Microsoft.Extensions.Logging;
using CommunityToolkit.Maui;

namespace Hestia;

public static class MauiProgram
{
	public static MauiApp CreateMauiApp()
	{
		var builder = MauiApp.CreateBuilder();
		builder
			.UseMauiApp<App>().UseMauiCommunityToolkit()
			.ConfigureFonts(fonts =>
			{
				fonts.AddFont("OpenSans-Regular.ttf", "OpenSansRegular");
				fonts.AddFont("OpenSans-Semibold.ttf", "OpenSansSemibold");
			});
		builder.Services.AddSingleton<MainPage>();
        builder.Services.AddSingleton<AdminPage>();
        builder.Services.AddSingleton<AdminController>();
        builder.Services.AddSingleton<ConfigPage>();
        builder.Services.AddSingleton<ConfigController>();
        builder.Services.AddSingleton<MonitoringPage>();
        builder.Services.AddSingleton<MonitoringController>();
        builder.Services.AddSingleton<Tabs>();
        builder.Services.AddSingleton<Server>();

#if DEBUG
        builder.Logging.AddDebug();
#endif

		return builder.Build();
	}
}
