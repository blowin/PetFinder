using PetFinder.Backend.Domain.Pets;

namespace PetFinder.Backend.Domain.Posts;

public class SearchPost : Post
{
    public Address Address { get; private set; }
    public List<Pet> Pets { get; private set; }

    private SearchPost()
        : this(string.Empty, string.Empty, new Address(), Guid.Empty, Enumerable.Empty<Pet>(), new List<Photo>())
    {
    }
    
    public SearchPost(string name, string description, Address address, Guid createdUserId, IEnumerable<Pet> pets, List<Photo> photos)
        : base(name, description, photos, createdUserId)
    {
        Address = address;
        Pets = new List<Pet>(pets);
    }
}