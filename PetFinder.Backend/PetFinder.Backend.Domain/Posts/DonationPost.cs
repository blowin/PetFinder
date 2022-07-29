using PetFinder.Backend.Domain.Pets;

namespace PetFinder.Backend.Domain.Posts;

public class DonationPost : Post
{
    public Pet Pet { get; private set; }
    
    public Address? Address { get; set; }

    private DonationPost()
        : this(string.Empty, string.Empty, Guid.Empty, new Pet(PetType.Cat), new List<Photo>())
    {
    }
    
    public DonationPost(string name, string description, Guid createdUserId, Pet pet, List<Photo> photos)
        : base(name, description, photos, createdUserId)
    {
        Pet = pet;
    }
}