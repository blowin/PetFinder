namespace PetFinder.Backend.Grpc.Extension;

public static class UUIDExt
{
    public static Guid ToGuid(this UUID self) => Guid.Parse(self.Value);
}