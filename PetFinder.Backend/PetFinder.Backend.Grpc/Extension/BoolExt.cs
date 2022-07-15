using Google.Protobuf.WellKnownTypes;

namespace PetFinder.Backend.Grpc.Extension;

public static class BoolExt
{
    public static NullableBool ToNullableBool(this bool? self)
    {
        return self == null ? 
            new NullableBool { Null = NullValue.NullValue } : 
            new NullableBool { Data = self.Value };
    }
}