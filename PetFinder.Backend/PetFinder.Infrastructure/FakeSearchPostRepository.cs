using Bogus;
using PetFinder.Backend.Domain;
using PetFinder.Backend.Domain.Pets;
using PetFinder.Backend.Domain.Posts;

namespace PetFinder.Infrastructure;

public class FakeSearchPostRepository : FakeRepository<SearchPostDetail>, ISearchPostRepository
{
    protected override IEnumerable<SearchPostDetail> GenerateItems()
    {
        return new Faker<SearchPostDetail>()
            .UseSeed(100)

            .RuleFor(e => e.Id, f => f.Random.Guid())
            .RuleFor(e => e.Title, f => f.Lorem.Sentence())
            .RuleFor(e => e.Description, f => f.Lorem.Lines().OrNull(f))
            // TODO
            .RuleFor(e => e.Photos, f => Array.Empty<Photo>())
            
            .GenerateForever()
            .Take(100);
    }
}