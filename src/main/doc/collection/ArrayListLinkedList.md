# ArrayList vs LinkedList


## ArrayList

- 基于数组实现
- 属性 `Object[] elementData` 为底层数组 
- 属性 `size` 注意调用add，remove方法的次数进行递增递减，所以当add一个null时也会递增

优缺点

- 基于数组实现，实现了RandomAccess接口具有随机访问特点，所以查找快速
- 顺序插入比较快，直接在数组后面添加元素，但涉及到数组扩容拷贝，存在性能损耗(拷贝：`Arrays.copyOf`)
- 随机下标插入或删除，必定涉及到数组拷贝，如果拷贝对象较多，性能耗损较大（拷贝：`System.arraycopy`）

因此 ArrayList适合顺序插入，随机访问场景。

特别说明

-  属性`elementData`是使用`transient`修饰，意味不希望数组被序列化，主要原因是数组未必是满的，序列化会导致不必要的性能消耗，ArrayList重写了`writeObject`方法。每次调用序列化的时候，调用defaultWriteObject序列化非`transient`字段，然后遍历`elementData`

-  属性`modCount`，对list的修改都会增加这个值，在迭代器初始化过程中`list.iterator()`,将`modCount`赋值给`expectModCount`，在迭代过程中判断两者是否相等，如果不等抛出异常`ConcurrentModificationException`(这也是一种*Fail-Fast*机制)，例如调用了`list.remove()`，但在迭代器中用iterator.remove不会存在此种异常

## LinkedList

- 基于链表实现：链表中任意一个元素都可以通过向前或者向后的方式获取其中前一个或后一个元素
- 属性`Node<E> first` , `Node<E> last`.内部类`Node(prev,element,next)`  
- 寻址查找只能同过first，last向后或向前遍历，所以查找具有一定的耗时
- 因为是链表结构，所以插入删除性能较高，只需要要插入的数据位置前后元素，不存在数据拷贝


## 两者特点比较
collection|是否容许为空|是否容许重复|是否有序|是否线程安全
---|---|---|---|---
ArrayList|可以为空|可以重复|有序|否
LinkedList|可以为空|可以重复|有序|否


## 问题
- LinkedList插入删除数据的速度一定比ArrayList快吗，why
- LinkedList使用for循环迭代性能非常差，需使用Iterator迭代器迭代，why





