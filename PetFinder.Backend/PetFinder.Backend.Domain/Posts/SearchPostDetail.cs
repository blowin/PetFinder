using PetFinder.Backend.Domain.Core;
using PetFinder.Backend.Domain.Posts.Photos;
using PetFinder.Backend.Domain.Users;

namespace PetFinder.Backend.Domain.Posts;

public record SearchPostDetail : IEntity
{
    public Guid Id { get; set; }
    public string Title { get; set; } = string.Empty;
    public string? Description { get; set; }
    public ICollection<Photo> Photos { get; set; } = Array.Empty<Photo>();
    public UserDetail User { get; set; } = new UserDetail();
    public DateTime CreateDate { get; set; }
}