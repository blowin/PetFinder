using PetFinder.Backend.Domain.Core;

namespace PetFinder.Backend.Domain.Posts;

public record UserDetail : IEntity
{
    public Guid Id { get; set; }
    public Photo? Avatar { get; set; }
    public string? Name { get; set; }
    public string? Surname { get; set; }
}