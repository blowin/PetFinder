namespace PetFinder.Backend.Grpc.Extension;

public static class GuidExt
{
    public static UUID ToUUID(this Guid self) => new UUID
    {
        Value = self.ToString("")
    };
}