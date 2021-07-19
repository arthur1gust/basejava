/**
 * Array based storage for Resumes
 *
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        if (get(r.uuid) == null) {
            storage[size] = r;
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
        boolean checkUuid = false;

        while (i < size) {
            if (storage[i].uuid.equals(uuid)) {
                checkUuid = true;
            }

            if (checkUuid) {
                storage[i] = storage[i + 1];
            }
            i++;
        }
        if (checkUuid) {
            size--;
        }
    }
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResume = new Resume[size];
        for (int i = 0; i < size; i++) {
            allResume[i] = storage[i];
        }
        return allResume;
    }

    int size() {
        return size;
    }
}
