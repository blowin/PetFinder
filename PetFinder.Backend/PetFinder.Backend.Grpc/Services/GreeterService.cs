using Google.Protobuf.Collections;
using Grpc.Core;
using PetFinder.Backend.Domain.Core;
using PetFinder.Backend.Domain.Pets;
using PetFinder.Backend.Domain.Posts;
using PetFinder.Backend.Grpc;
using PetFinder.Backend.Grpc.Extension;

namespace PetFinder.Backend.Grpc.Services;

public class GreeterService : Greeter.GreeterBase
{
    private readonly ILogger<GreeterService> _logger;
    private readonly IRepository<Pet> _petRepository;
    private readonly ISearchPostRepository _searchPostRepository;

    public GreeterService(ILogger<GreeterService> logger, IRepository<Pet> petRepository, ISearchPostRepository searchPostRepository)
    {
        _logger = logger;
        _petRepository = petRepository;
        _searchPostRepository = searchPostRepository;
    }

    public override Task<HelloReply> SayHello(HelloRequest request, ServerCallContext context)
    {
        return Task.FromResult(new HelloReply
        {
            Message = "Hello " + request.Name
        });
    }
    
    public override async Task GetPets(PageRequest request, IServerStreamWriter<PetResponse> responseStream, ServerCallContext context)
    {
        var pets = _petRepository
            .GetPage(request.ToCorePageRequest())
            .Select(pet => pet.ToPetResponse());

        foreach (var pet in pets)
            await responseStream.WriteAsync(pet);
    }

    public override Task<SearchPostDetail?> GetSearchPost(GetByIdRequest request, ServerCallContext context)
    {
        var id = request.Id.ToGuid();
        var postDetail = _searchPostRepository.GetById(id)?.ToSearchPostDetail();
        return Task.FromResult<SearchPostDetail?>(postDetail);
    }
}