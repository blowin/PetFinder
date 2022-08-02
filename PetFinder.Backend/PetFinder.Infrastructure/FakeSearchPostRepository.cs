using Bogus;
using PetFinder.Backend.Domain;
using PetFinder.Backend.Domain.Core;
using PetFinder.Backend.Domain.Posts;
using PetFinder.Backend.Domain.Users;

namespace PetFinder.Infrastructure;

public class FakeSearchPostRepository : FakeRepository<SearchPostDetail>, ISearchPostRepository
{
    private readonly IRepository<User> _userRepository;

    public FakeSearchPostRepository(IRepository<User> userRepository)
    {
        _userRepository = userRepository;
    }

    protected override IEnumerable<SearchPostDetail> GenerateItems()
    {
        var allItems = ((FakeRepository<User>)_userRepository).AllItems();
        return new Faker<SearchPostDetail>()
            .UseSeed(100)

            .RuleFor(e => e.Id, f => f.Random.Guid())
            .RuleFor(e => e.Title, f => f.Lorem.Sentence())
            .RuleFor(e => e.Description, f => f.Lorem.Lines().OrNull(f))
            .RuleFor(e => e.CreateDate, f => f.Date.Past().ToUniversalTime())
            // TODO: fixed after GetPostsPage
            .RuleFor(e => e.Photos, f => Array.Empty<Photo>())
            .RuleFor(e => e.User, f => ToUserDetail(f.PickRandom(allItems)))
            .GenerateForever()
            .Take(300);
    }

    public Page<SearchPostDetail> GetPostsPage(PageRequest pageRequest, ImageSize imageSize)
    {
        var page = GetPage(pageRequest);
        foreach (var searchPostDetail in page.Items)
        {
            Photo? deletePhoto = null;
            do
            {
                deletePhoto = searchPostDetail.Photos.FirstOrDefault(v => v.Size != imageSize);
                if(deletePhoto != null)
                    searchPostDetail.Photos.Remove(deletePhoto);

            } while (deletePhoto != null);
        }

        return page;
    }

    private UserDetail ToUserDetail(User user)
    {
        return new UserDetail
        {
            Id = user.Id,
            Name = user.Name,
            Surname = user.Surname,
            Avatar = user.Photo
        };
    }
}