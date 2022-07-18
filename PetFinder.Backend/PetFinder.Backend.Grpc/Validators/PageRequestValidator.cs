using FluentValidation;
using PetFinder.Backend.Domain.Core;

namespace PetFinder.Backend.Grpc.Validators;

public class PageRequestValidator : AbstractValidator<PageRequest>
{
    public PageRequestValidator()
    {
        RuleFor(e => e.Page).GreaterThan(0);
        RuleFor(e => e.PageSize).GreaterThan(0).LessThanOrEqualTo(Constants.MaxPageSize);
    }
}