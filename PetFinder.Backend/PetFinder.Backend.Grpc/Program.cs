using Calzolari.Grpc.AspNetCore.Validation;
using PetFinder.Backend.DependencyInjection;
using PetFinder.Backend.Grpc.Validators;
using PetService = PetFinder.Backend.Grpc.Services.PetService;

var builder = WebApplication.CreateBuilder(args);

// Additional configuration is required to successfully run gRPC on macOS.
// For instructions on how to configure Kestrel and gRPC clients on macOS, visit https://go.microsoft.com/fwlink/?linkid=2099682

// Add services to the container.
builder.Services.AddGrpc(options => options.EnableMessageValidation());
builder.Services.AddAppServices();

builder.Services.AddValidator<PageRequestValidator>(ServiceLifetime.Singleton);

builder.Services.AddGrpcValidation();

var app = builder.Build();

// Configure the HTTP request pipeline.
app.MapGrpcService<PetService>();
app.MapGet("/",
    () =>
        "Communication with gRPC endpoints must be made through a gRPC client. To learn how to create a client, visit: https://go.microsoft.com/fwlink/?linkid=2086909");

app.Run();