namespace PetFinder.Backend.Domain.Core;

public interface IReadRepository<T>
    where T : IEntity
{
    T GetById(Guid id);

    Page<T> GetPage(PageRequest pageRequest);
}

public interface IRepository<T> : IReadRepository<T>
    where T : IEntity
{
    void Add(T entity);
    void Update(T entity);
    void Delete(T entity);
}