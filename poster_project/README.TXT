Poster Methods:

1. Transparent Background: The method starts by drawing a white background on a blank image. Then it sets the alpha value
to 20%, resulting in faded background. The alpha value is set back to 100% and the pipe is drawn on top

2. Full Mirror: Creates a subimage of 1/4 the original. Flips and copies the image for each quadrent.

3. Recursion: The method starts with the original image and copies it 72 times, with each time the image being 
shrunk and rotated.

4. HSV Filter: This method goes over every pixel in the image. If the value of the pixel is >= 0.95, basically 
detecting the white background, it skips the pixel. Otherwise it generates a HSV color based on the x-value and 
the value of the pixel.

5. Invert Colors: Goes thorugh each pixel in the image, sets the pixel color to 255 minus the rgb values.

6. Pixelation: All this method does is scale down the image 16x to remove detail and then scales it back up to full size.