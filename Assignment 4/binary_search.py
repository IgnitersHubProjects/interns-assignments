'''Write a program that implements the binary search algorithm to search for a
specific element in a sorted array. The program should allow the user to input a
target element and then search for that element in the sorted array. It should
return the index of the element if found or indicate that the element is not in
the array.
Input
Enter the target element: 7
The array: [1, 3, 5, 7, 9, 11, 13, 15, 17, 19]]

Output
Element 7 is found at index 3.
'''

arr=[1, 3, 5, 7, 9, 11, 13, 15, 17, 19]
lb=0
ub=len(arr)-1
found=False
target=int(input("Enter the target value:"))
while lb<=ub:
    mid=(lb+ub)//2
    if arr[mid]==target:
        print("Element",target,"found at index",mid)
        found=True
        break
    elif arr[mid]<target:
        lb=mid+1
    else:
        ub=mid-1
if found==False:
    print("Element",target,"not found in the array")
