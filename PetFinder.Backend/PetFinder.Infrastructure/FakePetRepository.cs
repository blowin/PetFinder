using Bogus;
using PetFinder.Backend.Domain;
using PetFinder.Backend.Domain.Pets;

namespace PetFinder.Infrastructure;

public class FakePetRepository : FakeRepository<Pet>
{
    protected override IEnumerable<Pet> GenerateItems()
    {
        return new Faker<Pet>()
            .UseSeed(100)

            .RuleFor(e => e.Id, f => f.Random.Guid())
            .RuleFor(e => e.Type, f => f.PickRandom<PetType>())
            .RuleFor(e => e.MainPhoto, Photo.Empty)
            .RuleFor(e => e.Name, f => f.Person.FirstName.OrNull(f))
            .RuleFor(e => e.Age, f => f.Random.Byte(1, 15).OrNull(f))
            .RuleFor(e => e.Sterilized, f => f.Random.Bool().OrNull(f))
            .RuleFor(e => e.Vaccination, f => f.Random.WordsArray(f.Random.Int(0, 10)).OrNull(f))
            .RuleFor(e => e.Diseases, f => f.Random.WordsArray(f.Random.Int(0, 10)))
            .RuleFor(e => e.Notes, f => f.Lorem.Sentence().OrNull(f))

            .GenerateForever()
            .Take(100);
    }
}