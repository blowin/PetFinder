using PetFinder.Backend.Domain.Pets;

namespace PetFinder.Backend.Domain.Posts;

public class SearchPost : Entity
{
    public string Name { get; private set; }
    public string Description { get; private set; }
    public Address Address { get; private set; }
    public Guid CreatedUserId { get; private set; }
    public List<Pet> Pets { get; private set; }
    public List<Photo>? Photos { get; set; }
    public DateTime? CloseDate { get; set; }

    private SearchPost()
        : this(string.Empty, string.Empty, new Address(), Guid.Empty, Enumerable.Empty<Pet>())
    {
    }
    
    public SearchPost(string name, string description, Address address, Guid createdUserId, IEnumerable<Pet> pets)
    {
        Name = name;
        Description = description;
        Address = address;
        CreatedUserId = createdUserId;
        Pets = new List<Pet>(pets);
    }
}