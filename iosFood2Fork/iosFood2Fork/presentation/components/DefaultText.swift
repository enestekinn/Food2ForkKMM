//
//  DefaultText.swift
//  iosFood2Fork
//
//  Created by Enes Tekin on 24.06.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct DefaultText: View {
    
    private let text: String
    private let size: CGFloat
    
    init(_ text:String, size:CGFloat = 15){
        self.text = text
        self.size = size
        
        }
    
    
    
    var body: some View {
    Text(text)
        .font(Font.custom("Avenir", size: size))
    }
}

//struct DefaultText_Previews: PreviewProvider {
//    static var previews: some View {
//        DefaultText()
//    }
//}
