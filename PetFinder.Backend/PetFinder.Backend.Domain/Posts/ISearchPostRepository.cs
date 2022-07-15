using PetFinder.Backend.Domain.Core;

namespace PetFinder.Backend.Domain.Posts;

public interface ISearchPostRepository : IReadRepository<SearchPostDetail>
{
    Page<SearchPostDetail> GetPostsPage(PageRequest pageRequest, ImageSize imageSize);
}