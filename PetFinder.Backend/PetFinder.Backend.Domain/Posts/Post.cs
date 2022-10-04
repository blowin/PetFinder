using PetFinder.Backend.Domain.Core;
using PetFinder.Backend.Domain.Posts.Photos;

namespace PetFinder.Backend.Domain.Posts;

public abstract class Post : Entity
{
    public string Name { get; private set; }
    public string Description { get; private set; }

    public List<Photo>? Photos { get; set; }
    public DateTime CreateDate { get; set; } = DateTime.UtcNow;
    public Guid CreateUserId { get; set; }
    public DateTime? CloseDate { get; set; }

    protected Post(string name, string description, List<Photo>? photos, Guid createUserId)
    {
        Name = name;
        Description = description;
        Photos = photos;
        CreateUserId = createUserId;
    }
}