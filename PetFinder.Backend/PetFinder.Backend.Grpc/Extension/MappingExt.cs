using Google.Protobuf;
using Google.Protobuf.WellKnownTypes;
using PetFinder.Backend.Domain.Pets;

namespace PetFinder.Backend.Grpc.Extension;

public static class MappingExt
{
    public static SearchPostDetail? ToSearchPostDetail(this Domain.Posts.SearchPostDetail self)
    {
        return new SearchPostDetail
        {
            Id = self.Id.ToUuid(),
            Title = self.Title,
            Description = self.Description.ToNullableString(),
            Photos =
            {
                self.Photos?.Select(i => i.ToPhoto()) ?? Enumerable.Empty<Photo>()
            },
            User = self.User.ToUserDetail(),
            CreateDate = Timestamp.FromDateTime(self.CreateDate)
        };
    }

    public static UserDetail ToUserDetail(this Domain.Posts.UserDetail self) => new UserDetail
    {
        Id = self.Id.ToUuid(),
        Avatar = self.Avatar?.ToNullablePhoto(),
        Name = self.Name.ToNullableString(),
        Surname = self.Surname.ToNullableString()
    };

    public static UUID ToUuid(this Guid self) => new UUID
    {
        Value = self.ToString("")
    };

    public static NullableBool ToNullableBool(this bool? self)
    {
        return self == null ? 
            new NullableBool { Null = NullValue.NullValue } : 
            new NullableBool { Data = self.Value };
    }

    public static NullablePhoto ToNullablePhoto(this Domain.Photo? self)
    {
        return self == null ? new NullablePhoto { Null = NullValue.NullValue } : new NullablePhoto { Data = self.ToPhoto() };
    }

    public static Photo ToPhoto(this Domain.Photo self)
    {
        return new Photo
        {
            Id = self.Id.ToUuid(),
            Size = (ImageSize)self.Size,
            Content = ByteString.CopyFrom(self.Content)
        };
    }

    public static NullableString ToNullableString(this string? self)
    {
        return string.IsNullOrEmpty(self) ? 
            new NullableString { Null = NullValue.NullValue } : 
            new NullableString { Data = self };
    }

    public static Guid ToGuid(this UUID self) => Guid.Parse(self.Value);

    public static Domain.Core.PageRequest ToCorePageRequest(this PageRequest self) => new Domain.Core.PageRequest
    {
        PageSize = self.PageSize,
        Page = self.Page
    };

    public static Domain.Core.ImageSize ToCoreImageSize(this ImageSize self) => (Domain.Core.ImageSize)self;

    public static PetResponse ToPetResponse(this Pet self)
    {
        return new PetResponse
        {
            Id = self.Id.ToUuid(),
            Type = (PetType)self.Type,
            Name = self.Name,
            Age = self.Age,
            Sterilized = self.Sterilized.ToNullableBool(),
            Diseases =
            {
                self.Diseases ?? Enumerable.Empty<string>()
            },
            Vaccination =
            {
                self.Vaccination ?? Enumerable.Empty<string>()
            }
        };
    }
}