namespace PetFinder.Backend.Grpc.Extension;

public static class PageRequestExt
{
    public static Domain.Core.PageRequest ToCorePageRequest(this PageRequest self) => new Domain.Core.PageRequest
    {
        PageSize = self.PageSize,
        Page = self.Page
    };
}