package com.ds.sort;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.swap;

public class MaxHeap<E extends Comparable<E>> {

    private List<E> data;

    private int heapSize;


    public MaxHeap(int capacity) {
        data = new ArrayList<>(capacity);

    }

    public MaxHeap(List<E> data) {
        this.data = data;
        heapSize = data.size();
    }


    public int getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }


    private int parent(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("no parent");

        }
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }


    /**
     * 二叉树 的left(k)子树和right(k)子树满足堆的性质，但是data[k] 可能小于left(k)或right(k)
     * 将其向下调整，一般也称这个过程为shift down
     *
     * @param k
     */

    private void heapify(int k) {
        int left = left(k);
        int right = right(k);
        int maxIndex = k;
        if (left < heapSize && data.get(left).compareTo(data.get(maxIndex)) > 0) {
            maxIndex = left;
        }
        if (right < heapSize && data.get(right).compareTo(data.get(maxIndex)) > 0) {
            maxIndex = right;
        }
        if (k != maxIndex) {
            swap(data, maxIndex, k);
            heapify(maxIndex);

        }
    }

    /**
     * 使用数组随机构建最小堆的方式为
     * 从最后一个元素的parent开始 到0个元素，依次调用heapify
     */

    public void buildMaxHeap() {
        int lastNodeParent = parent(data.size() - 1);
        for (int i = lastNodeParent; i >= 0; i--) {
            heapify(i);
        }
    }

    /**
     * 通过将堆顶元素移除（将堆顶元素与最后一个元素交换位置，然后将堆的大小减一）实现
     */

    public void heapSort() {
        buildMaxHeap();
        for (int i = heapSize - 1; i > 0; i--) {
            swap(data, 0, heapSize - 1);
            setHeapSize(heapSize - 1);
            heapify(0);
        }

    }

    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(4, 1, 3, 2, 16, 9, 10, 14, 8, 7);
        MaxHeap<Integer> maxHeap = new MaxHeap<>(data);
        maxHeap.heapSort();
        System.out.println(data);
    }


}
