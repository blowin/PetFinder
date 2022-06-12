namespace PetFinder.Backend.Domain.Core;

public interface IRepository<T>
    where T : Entity
{
    void Add(T entity);
    void Update(T entity);
    void Delete(T entity);
    
    T GetById(Guid id);

    IEnumerable<T> GetAll();
}