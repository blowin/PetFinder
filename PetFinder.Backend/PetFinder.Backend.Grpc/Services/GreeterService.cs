using Google.Protobuf.Collections;
using Grpc.Core;
using PetFinder.Backend.Domain.Core;
using PetFinder.Backend.Domain.Pets;
using PetFinder.Backend.Grpc;

namespace PetFinder.Backend.Grpc.Services;

public class GreeterService : Greeter.GreeterBase
{
    private readonly ILogger<GreeterService> _logger;
    private IRepository<Domain.Pets.Pet> _petRepository;

    public GreeterService(ILogger<GreeterService> logger, IRepository<Domain.Pets.Pet> petRepository)
    {
        _logger = logger;
        _petRepository = petRepository;
    }

    public override Task<HelloReply> SayHello(HelloRequest request, ServerCallContext context)
    {
        return Task.FromResult(new HelloReply
        {
            Message = "Hello " + request.Name
        });
    }
    
    public override async Task GetAllPets(EmptyRequest request, IServerStreamWriter<PetResponse> responseStream, ServerCallContext context)
    {
        var pets = _petRepository.GetAll().Take(10)
            .Select(pet => new PetResponse
            {
                Id = new UUID
                {
                    Value = pet.Id.ToString("")
                },
                Type = (PetType)pet.Type,
                Name = pet.Name,
                Age = pet.Age,
                Sterilized = new NullableBool
                {
                    NullValue = pet.Sterilized == null,
                    Value = pet.Sterilized ?? false
                },
                Diseases =
                {
                    pet.Diseases ?? Enumerable.Empty<string>()
                },
                Vaccination =
                {
                    pet.Vaccination ?? Enumerable.Empty<string>()
                }
            });

        foreach (var pet in pets)
        {
            await responseStream.WriteAsync(pet);
        }
    }
}