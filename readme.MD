## FourSquareView

### Android ui module to add a foursquareview in your app.

### Usage

#### Adding FourSquareViewList in activity

```
    FourSquareViewList fourSquareViewList = new FourSquareViewList(this);
```

#### FourSquareViewList and add a view

```
    fourSquareViewList.addView(color1,color2,color3,color4,new OnCompleteFillListener(){
        void onFill() {

        }
        void onEmpty() {

        }
    });
```

#### Showing the FourSquareViewList

```
    fourSquareViewList.show();
```
