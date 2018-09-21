# CopyOnWriteArrayList

## 原理
copyOnWriteArrayList相当于线程安全的ArrayList，写入时，重新copy原集合，对新集合进行修改后，在将指针指向新集合。底层也是通过数组实现

+ 在`add`中，先获取独占锁，防止多线程同时修改数据
+ 添加元素通过复制原数组方式，将新数据添加到新数组的len位置，在通过setArray()来重新指向被volatile修饰的array

```

 public boolean add(E e) {
        final ReentrantLock lock = this.lock;                       // 获取独占锁
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);// 重新生成一个新的数组实例，并将原始数组的元素拷贝到新数组中
            newElements[len] = e;                                   // 添加新的元素到新数组的末尾
            setArray(newElements);                                  // 更新底层数组
            return true;
        } finally {
            lock.unlock();
        }

```

## 特点

- 线程安全
- 适合集合不大，读多写少的场景
- 对实时性要求不高的场景，读会最终一致性

CopyOnWriteArrayList 