/**
 * Array based storage for Resumes
 *
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        if (get(r.uuid) == null) {
            storage[size()] = r;
            size++;
        } else {
            System.out.println(r.uuid + " -already exist, please another uuid");
        }
    }

    Resume get(String uuid) {
        for(int i = 0; i < size; i++) {
            if(uuid.equals(storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int i = 0;

        while (i < size && storage[i].uuid != uuid) {
            i++;
        }
        for (int j = i; j < size - 1; j++) {
            storage[j].uuid = storage[j + 1].uuid;
        }
        storage[size - 1] = null;
        size--;
    }
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] tempStorage = new Resume[size];
        for (int i = 0; i < size; i++) {
            tempStorage[i] = storage[i];
        }
        return tempStorage;
    }

    int size() {
        return size;
    }
}
