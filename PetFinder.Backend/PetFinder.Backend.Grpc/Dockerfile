FROM mcr.microsoft.com/dotnet/aspnet:6.0 AS base
WORKDIR /app
EXPOSE 80
EXPOSE 443

FROM mcr.microsoft.com/dotnet/sdk:6.0 AS build
WORKDIR /src
COPY ["PetFinder.Backend.Grpc/PetFinder.Backend.Grpc.csproj", "PetFinder.Backend.Grpc/"]
COPY ["PetFinder.Backend.DependencyInjection/PetFinder.Backend.DependencyInjection.csproj", "PetFinder.Backend.DependencyInjection/"]
COPY ["PetFinder.Infrastructure/PetFinder.Infrastructure.csproj", "PetFinder.Infrastructure/"]
COPY ["PetFinder.Backend.Domain/PetFinder.Backend.Domain.csproj", "PetFinder.Backend.Domain/"]
RUN dotnet restore "PetFinder.Backend.Grpc/PetFinder.Backend.Grpc.csproj"
COPY . .
WORKDIR "/src/PetFinder.Backend.Grpc"
RUN dotnet build "PetFinder.Backend.Grpc.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "PetFinder.Backend.Grpc.csproj" -c Release -o /app/publish /p:UseAppHost=false

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "PetFinder.Backend.Grpc.dll"]