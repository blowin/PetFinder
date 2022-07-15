using Google.Protobuf;

namespace PetFinder.Backend.Grpc.Extension;

public static class PhotoExt
{
    public static Photo ToPhoto(this Domain.Photo self)
    {
        return new Photo
        {
            Id = self.Id.ToUUID(),
            Size = (ImageSize)self.Size,
            Content = ByteString.CopyFrom(self.Content)
        };
    }
}