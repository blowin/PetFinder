using Microsoft.Extensions.DependencyInjection;
using PetFinder.Infrastructure;

namespace PetFinder.Backend.DependencyInjection
{
    public static class ServiceCollectionExt
    {
        public static IServiceCollection AddAppServices(this IServiceCollection self)
        {
            self.Scan(s =>
            {
                s.FromAssemblyOf<FakePetRepository>()
                    .AddClasses(i => i.AssignableTo(typeof(FakeRepository<>))).AsImplementedInterfaces();
            });

            return self;
        }
    }
}