# FragmentShareElementTransition
Examples of shared element transition between fragments

In this example, all window transitions are `explode` and there is a shared element transition between the first activity and a fragment contained in the second activity. Then there is a sliding transition between 2 fragments with a shared element transition.

## Shared elements
To enable a shared element transition on fragments, 3 steps:

*Notify the transaction that there will be a shared element transition*
```java
getFragmentManager().beginTransaction()
    .addSharedElement(view, transitionName);
```

*Set the shareed element transition to the fragment*
__Java__
```java
fragment.setSharedElementEnterTransition(
    TransitionInflater.from(context).inflateTransition(R.transition.change_transform));
// or
fragment.setSharedElementEnterTransition(new ChangeTransform());
```
__XML__
```xml
<item name="android:fragmentSharedElementEnterTransition">@transition/change_transform</item>
```

*Set the transition name*
If you are using a list/grid/... View, you need to set the transition name dynamically to the view that will be shared and to the view that should receive the shared element:
```java
view.setTransitionName(transitionName);
```
if it is a single element, you can set the transition name in the xml:
```xml
android:transitionName="transition"
```

## Transitions
You can set a transition to a fragment:
```java
fragment.setSharedElementEnterTransition(new Explode());
```
```xml
<item name="android:fragmentEnterTransition">@transition/explode</item>
```

## Examples

With shared element transition | Without shared element transition
------------ |  -------------
![Demo](images/githubfragmentsharedtransition.gif) | ![Demo](images/githubfragmenttransition.gif)

## Tricks
- Pressing back to go to the first activity:
```java
@Override
    public void onBackPressed() {
        if (mDogListFragment.isVisible()) {
            // override doglistfragment exit to have a different transition
            mDogListFragment.setExitTransition(TransitionInflater.from(this).inflateTransition(R.transition.slide_bottom));
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new Fragment())
                    .commit();
        }
        super.onBackPressed();
    }
```
In the documentation : 
> Sets the Transition that will be used to move Views out of the scene when the fragment is removed, hidden, or detached when not popping the back stack.

This means that if we click back, since this fragment is not on the backstack, it's not popped and then the exit transition won't be applied. So I replace the current fragment with an empty one just before going back to the previous activity to force the transition to happen.
 
- Bug in the display of examples

If you look closely in the examples, you can see that when a dog from the list (the 3 dogs list) is selected, the 3rd one is cut during the transition. This come from the fact the replacing fragment is smaller than the previous one and Android animate only the part corresponding to the future element size and remove the rest. This bug can be easily avoided by having fragments of the same size.
