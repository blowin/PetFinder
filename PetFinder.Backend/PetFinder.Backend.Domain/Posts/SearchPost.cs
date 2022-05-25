using PetFinder.Backend.Domain.Pets;

namespace PetFinder.Backend.Domain.Posts;

public class SearchPost : Entity
{
    public string Name { get; private set; }
    public string Description { get; private set; }
    public Address Address { get; private set; }
    public Guid CreatedUserId { get; private set; }
    public Pet[] Pets { get; private set; }
    public Photo[]? Photos { get; set; }
    public DateTime? CloseDate { get; set; }

    public SearchPost()
        : this(string.Empty, string.Empty, new Address(string.Empty, 0, 0), Guid.Empty, Array.Empty<Pet>())
    {
    }
    
    public SearchPost(string name, string description, Address address, Guid createdUserId, Pet[] pets)
    {
        Name = name;
        Description = description;
        Address = address;
        CreatedUserId = createdUserId;
        Pets = pets;
    }
}