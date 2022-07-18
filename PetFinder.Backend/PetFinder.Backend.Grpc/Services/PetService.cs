using Google.Protobuf.Collections;
using Grpc.Core;
using PetFinder.Backend.Domain.Core;
using PetFinder.Backend.Domain.Pets;
using PetFinder.Backend.Domain.Posts;
using PetFinder.Backend.Grpc;
using PetFinder.Backend.Grpc.Extension;

namespace PetFinder.Backend.Grpc.Services;

public class PetService : PetServiceGrpc.PetServiceGrpcBase
{
    private readonly IRepository<Pet> _petRepository;
    private readonly ISearchPostRepository _searchPostRepository;

    public PetService(IRepository<Pet> petRepository, ISearchPostRepository searchPostRepository)
    {
        _petRepository = petRepository;
        _searchPostRepository = searchPostRepository;
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

    public override async Task GetSearchPosts(GetSearchPostsRequest request, IServerStreamWriter<SearchPostDetail> responseStream, ServerCallContext context)
    {
        var page = request.PageRequest.ToCorePageRequest();

        foreach (var detail in _searchPostRepository.GetPostsPage(page, request.ImageSize.ToCoreImageSize()))
        {
            var responseDetail = detail.ToSearchPostDetail();
            if(responseDetail != null)
                await responseStream.WriteAsync(responseDetail);
        }
    }
}