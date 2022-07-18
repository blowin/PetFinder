using PetFinder.Backend.Domain.Core;

namespace PetFinder.Backend.Domain.Posts;

public record SearchPostDetail : IEntity
{
    public Guid Id { get; set; }
    public string Title { get; set; } = string.Empty;
    public string? Description { get; set; }
    public ICollection<Photo> Photos { get; set; } = Array.Empty<Photo>();
}
