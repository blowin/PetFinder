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
        using var client = new HttpClient();

        var sizes = new Dictionary<ImageSize, (int Height, int Width)>
        {
            { ImageSize.Xs, (100, 100) },
            { ImageSize.Sm, (150, 150) },
            { ImageSize.Md, (200, 200) },
            { ImageSize.Lg, (250, 250) },
        };
        var sizeKeys = sizes.Keys.ToArray();
        var allItems = ((FakeRepository<User>)_userRepository).AllItems();
        return new Faker<SearchPostDetail>()
            .UseSeed(100)

            .RuleFor(e => e.Id, f => f.Random.Guid())
            .RuleFor(e => e.Title, f => f.Lorem.Sentence())
            .RuleFor(e => e.Description, f => f.Lorem.Lines().OrNull(f))
            .RuleFor(e => e.CreateDate, f => f.Date.Past().ToUniversalTime())
            .Rules((f, e) =>
            {
                var result = new Photo[sizeKeys.Length];
                for (var i = 0; i < sizeKeys.Length; i++)
                {
                    var size = sizeKeys[i];
                    var requiredSize = sizes[size];
                    var base64 = f.Image.DataUri(requiredSize.Width, requiredSize.Height);
                    result[i] = new Photo(base64, size)
                    {
                        Id = f.Random.Guid()
                    };
                }

                e.Photos = result;
            })
            .RuleFor(e => e.User, f => ToUserDetail(f.PickRandom(allItems)))
            .GenerateForever()
            .Take(300);
    }

    public Page<SearchPostDetail> GetPostsPage(PageRequest pageRequest, ImageSize imageSize)
    {
        var page = GetPage(pageRequest);

        foreach (var searchPostDetail in page.Items)
            searchPostDetail.Photos = searchPostDetail.Photos.Where(v => v.Size == imageSize).ToArray();

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