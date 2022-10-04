using PetFinder.Backend.Domain.Core;
using PetFinder.Backend.Domain.Posts.Photos;

namespace PetFinder.Backend.Domain.Users;

public record UserDetail : IEntity
{
    public Guid Id { get; set; }
    public Photo? Avatar { get; set; }
    public string? Name { get; set; }
    public string? Surname { get; set; }
}