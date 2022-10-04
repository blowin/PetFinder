using PetFinder.Backend.Domain.Core;
using PetFinder.Backend.Domain.Posts.Photos;

namespace PetFinder.Backend.Domain.Posts;

public interface ISearchPostRepository : IReadRepository<SearchPostDetail>
{
    Page<SearchPostDetail> GetPostsPage(PageRequest pageRequest, ImageSize imageSize);
}