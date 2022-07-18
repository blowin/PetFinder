using PetFinder.Backend.Domain.Core;

namespace PetFinder.Backend.Domain.Posts;

public abstract class Post : Entity
{
    public List<Photo>? Photos { get; set; }
    
    protected Post(List<Photo>? photos)
    {
        Photos = photos;
    }
}