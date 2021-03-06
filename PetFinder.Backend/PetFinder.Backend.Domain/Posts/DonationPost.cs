using PetFinder.Backend.Domain.Pets;

namespace PetFinder.Backend.Domain.Posts;

public class DonationPost : Post
{
    public string Name { get; private set; }
    public string Description { get; private set; }
    public Pet Pet { get; private set; }
    public Guid CreatedUserId { get; private set; }
    
    public Address? Address { get; set; }
    public DateTime? CloseDate { get; set; }

    private DonationPost()
        : this(string.Empty, string.Empty, Guid.Empty, new Pet(PetType.Cat), new List<Photo>())
    {
    }
    
    public DonationPost(string name, string description, Guid createdUserId, Pet pet, List<Photo> photos)
        : base(photos)
    {
        Name = name;
        Description = description;
        Pet = pet;
        CreatedUserId = createdUserId;
    }
}