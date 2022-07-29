using Bogus;
using PetFinder.Backend.Domain.Users;

namespace PetFinder.Infrastructure;

public class FakeUserRepository : FakeRepository<User>
{
    protected override IEnumerable<User> GenerateItems()
    {
        var i = 1;
        var socialNetworks = new[]
        {
            "Instagram",
            "YouTube",
            "Facebook",
            "Twitter",
            "TikTok",
            "Pinterest",
            "Snapchat",
            "VK",
        };
        return new Faker<User>()
            .UseSeed(100)

            .RuleFor(e => e.Id, f => f.Random.Guid())
            .RuleFor(e => e.Name, f => f.Person.FirstName)
            .RuleFor(e => e.Surname, f => f.Person.LastName)
            .RuleFor(e => e.PhoneNumber, f => f.Phone.PhoneNumber())
            .RuleFor(e => e.Login, f => string.Format("{0}_{1}", f.Hacker.Abbreviation(), ++i))
            .RuleFor(e => e.Password, f => f.Internet.Password())
            .Rules((f, e) =>
            {
                if (!f.Random.Bool()) 
                    return;
                
                var len = f.Random.Number(1, socialNetworks.Length);
                foreach (var soc in f.PickRandom(socialNetworks, len))
                {
                    var url = f.Internet.Url();
                    var socialNetwork = new SocialNetwork(soc, url);
                    e.SocialNetworks.Add(socialNetwork);
                }
            })
            .GenerateForever()
            .Take(100);
    }
}